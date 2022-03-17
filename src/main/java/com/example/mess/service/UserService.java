package com.example.mess.service;



import com.example.mess.entity.UnitEntity;
import com.example.mess.entity.UserEntity;
import com.example.mess.exception.GeneralException;
import com.example.mess.mapper.UserMapper;
import com.example.mess.model.LoginRequest;
import com.example.mess.model.TimeStamp;
import com.example.mess.model.User;
import com.example.mess.repository.UnitsRepository;
import com.example.mess.repository.UserRepository;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final UnitsRepository unitsRepository;
    public String addUser(User model){
         repository.save(mapper.mToE(model));
         return "Successful";
    }


    public User login(LoginRequest request) throws GeneralException {
        return repository.findByEmailAndPassword(request.getEmail(),request.getPassword())
                .map(mapper::eToM).orElseThrow(
                        ()-> new GeneralException("Invalid Credentials"));
    }

    public User findById(Integer id) throws GeneralException {
        return repository.findById(id).map(mapper::eToM)
                .orElseThrow(()-> new GeneralException("RECORD NOT FIND"));
    }

    public void deleteById(Integer id) throws GeneralException {
        User user = this.findById(id);
        repository.deleteById(id);
    }


    public void export(HttpServletResponse response, Integer userId, TimeStamp timeStamp) throws IOException, GeneralException {

        Optional<UserEntity> userEntity = repository.findById(userId);
        if (userEntity.isEmpty()){
            throw new GeneralException("User Not Found");
        }

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("Report For "+userEntity.get().getUsername()+" ",fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.COURIER);
        fontParagraph.setSize(12);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.5f, 2.0f, 2.0f, 2.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(userId,table, timeStamp.getDateBefore(), timeStamp.getDateAfter());

    }

    public void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Date", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Consumed Units", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Units Off", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Guests Units", font));
        table.addCell(cell);


    }

    public void writeTableData(Integer userId, PdfPTable table, Timestamp dateBefore, Timestamp dateAfter){

        List<UnitEntity> entities = unitsRepository.findByUserIdAndDateBetweenOrderByDateAsc(userId, dateBefore, dateAfter);

        entities.forEach(unitEntity -> {
            table.addCell(unitEntity.getDate().toString());
            table.addCell(unitEntity.getUnits().toString());
            table.addCell(unitEntity.getUnits().toString());
            table.addCell(unitEntity.getUnits().toString());
        });
    }
}
