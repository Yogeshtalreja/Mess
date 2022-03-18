package com.example.mess.service;



import com.example.mess.entity.*;
import com.example.mess.exception.GeneralException;
import com.example.mess.mapper.UserMapper;
import com.example.mess.model.LoginRequest;
import com.example.mess.model.TimeStamp;
import com.example.mess.model.User;
import com.example.mess.repository.*;
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
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final UnitsRepository unitsRepository;
    private final UnitOffRepository unitOffRepository;
    private final GuestRepository guestRepository;
    private final MessRepository messRepository;

    public String addUser(User model) throws GeneralException {

        Optional<MessEntity> messEntity = messRepository.findById(model.getMess_id());
        if (messEntity.isEmpty()){
            throw new GeneralException("Mess With This ID Not Found");
        }
        UserEntity entity = mapper.mToE(model);
        entity.setMess(messEntity.get());
         repository.save(entity);
         return "Successful";
    }


    public User login(LoginRequest request) throws GeneralException {
        Optional<UserEntity> optional = repository.findByEmailAndPassword(request.getEmail(),request.getPassword());
        if (optional.isEmpty()){
            throw new GeneralException("Invalid Credentials");
        }
        User user = mapper.eToM(optional.get());
        user.setMess_id(optional.get().getMess().getId());
        return user;
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

        document.add(paragraph);


        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.5f, 2.0f, 2.0f, 2.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(userId,table, timeStamp.getDateBefore(), timeStamp.getDateAfter());

        document.add(table);
        document.close();
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

        Font fontParagraph = FontFactory.getFont(FontFactory.COURIER);
        fontParagraph.setSize(12);



        List<UnitEntity> entities = unitsRepository.findByUserIdAndDateBetweenOrderByDateAsc(userId, dateBefore, dateAfter);
        for (UnitEntity entity : entities){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            timestamp.setHours(0);
            timestamp.setMinutes(0);
            timestamp.setSeconds(0);
            timestamp.setDate(entity.getDate().getDate());

            Timestamp timestampTwo = new Timestamp(System.currentTimeMillis());
            timestampTwo.setHours(23);
            timestampTwo.setMinutes(59);
            timestampTwo.setSeconds(59);
            timestampTwo.setDate(timestamp.getDate());

            int unitOff = 0;
            int guestUnits = 0;
            List<UnitOffEntity> unitOffEntities = unitOffRepository.findByUserIdAndDateBetween(userId,timestamp,timestampTwo);
            if (unitOffEntities!=null){

                for (UnitOffEntity unitOffEntity: unitOffEntities){
                    if (unitOffEntity.getDinner()){
                        unitOff = unitOff + 1;
                    }
                    if (unitOffEntity.getLunch()){
                        unitOff = unitOff + 1;
                    }
                    if (unitOffEntity.getBreakfast()){
                        unitOff = unitOff + 1;
                    }

                }

            }

            List<GuestEntity> guestEntities = guestRepository.findAllByUserIdAndDateBetween(userId,timestamp,timestampTwo);
            if (guestEntities!=null){
               for (GuestEntity guestEntity : guestEntities){
                   if (guestEntity.getInDinner()){
                       guestUnits = guestUnits + 1;
                   }
                   if (guestEntity.getInLunch()){
                       guestUnits = guestUnits + 1;
                   }
                   if (guestEntity.getInBreakfast()){
                       guestUnits = guestUnits + 1;
                   }
               }
            }
            table.addCell(""+entity.getDate());
            table.addCell(String.valueOf(entity.getUnits() - guestUnits));
            table.addCell(String.valueOf(unitOff));
            table.addCell(String.valueOf(guestUnits));
        }
    }
}
