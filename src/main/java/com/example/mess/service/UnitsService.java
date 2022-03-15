package com.example.mess.service;

import com.example.mess.entity.*;
import com.example.mess.exception.GeneralException;
import com.example.mess.mapper.GuestMapper;
import com.example.mess.model.Dashboard;
import com.example.mess.model.UnitOff;
import com.example.mess.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UnitsService {

    private final UnitOffRepository unitOffRepository;
    private final UserRepository userRepository;
    private final GuestMapper guestMapper;
    private final UnitsRepository unitsRepository;
    private final GuestRepository guestRepository;
    private final ExpenseRepository expenseRepository;
    private final PaymentRepository paymentRepository;

    public Dashboard findDashboardApis(Integer userId) throws GeneralException {
        Optional<UserEntity> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new GeneralException("User Not Found");
        }
        Integer totalUnits = totalUnitsOfCurrentMonth(userId);
        Integer totalUnitsOff = totalOffUnits(userId);
        Integer totalGuestsUnits = totalGuestsUnits(userId);
        Integer totalMessBill = totalMessBill(user.get());
        Integer totalMessUsers = totalUsersOfMess(user.get().getMess().getId());
        Double unitPrice = calculateUnitPriceOfMonth(user.get().getMess().getId(),totalMessBill);
        Double totalSpent = totalSpentPrice(totalUnits,unitPrice);
        Integer paidAmount = paidAmount(userId);

        return new Dashboard(totalUnits,totalUnitsOff,totalGuestsUnits,totalMessBill,
                unitPrice,totalMessUsers,totalSpent,(paidAmount - totalSpent),paidAmount);


    }

    public String addUnitsOff(UnitOff model) throws GeneralException {
        Optional<UserEntity> user = userRepository.findById(model.getUserId());
        if(user.isEmpty()){
            throw new GeneralException("User Not Found");
        }
        UnitOffEntity entity = guestMapper.mToE(model);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        entity.setUser(user.get());
        entity.setCreatedAt(timestamp);
        unitOffRepository.save(entity);
        return "Successful";
    }

    public Integer totalUnitsOfCurrentMonth(Integer userId) throws GeneralException {
        Optional<UserEntity> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new GeneralException("User Not Found");
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        timestamp.setDate(1);
        List<UnitEntity> unitEntityList = unitsRepository.findBySameMonth(userId,timestamp);

        Integer totalUnits = 0;
        for (UnitEntity unit : unitEntityList){
            totalUnits = totalUnits + unit.getUnits();
        }
        return totalUnits;
    }

    public Integer totalOffUnits(Integer userId){
        Integer totalOffUnits = 0;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        timestamp.setDate(1);
        List<UnitOffEntity> unitOffEntities = unitOffRepository.findBySameMonth(userId,timestamp);
        for(UnitOffEntity entity : unitOffEntities){
            if (entity.getBreakfast()){
                totalOffUnits = totalOffUnits + 1;
            }
            if (entity.getLunch()){
                totalOffUnits = totalOffUnits + 1;
            }
            if (entity.getDinner()){
                totalOffUnits = totalOffUnits + 1;
            }
        }
        return totalOffUnits;
    }

    public Integer totalGuestsUnits(Integer userId){
        Integer totalGuests = 0;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        timestamp.setDate(1);
        List<GuestEntity> guestEntities = guestRepository.findBySameMonth(userId,timestamp);
        for(GuestEntity entity : guestEntities){
            if (entity.getInBreakfast()){
                totalGuests = totalGuests + 1;
            }
            if (entity.getInLunch()){
                totalGuests = totalGuests + 1;
            }
            if (entity.getInDinner()){
                totalGuests = totalGuests + 1;
            }
        }
        return totalGuests;
    }

    public Integer totalMessBill(UserEntity userEntity){

        Integer totalBill = 0;

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        timestamp.setDate(1);
        List<ExpenseEntity> expenseEntities = expenseRepository.findBySameMonth(userEntity.getMess().getId(), timestamp);
        for (ExpenseEntity entity : expenseEntities){
            totalBill = totalBill + entity.getAmount();
        }
        return totalBill;
    }

    public Integer totalUsersOfMess(String messId){
        return userRepository.totalUsersOfMess(messId);
    }

    public Double calculateUnitPriceOfMonth(String messId, Integer totalMessAmount){
        Integer totalUnits = 0;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        timestamp.setDate(1);
        List<UnitEntity> entities = unitsRepository.findUnitsOfMessByMonth(messId,timestamp);
        for(UnitEntity entity: entities){
            totalUnits = totalUnits + entity.getUnits();
        }

        return Double.valueOf(totalMessAmount / totalUnits);
    }

        public Double totalSpentPrice(Integer totalUnits, Double unitPrice){
            return totalUnits * unitPrice;
        }

    public Integer paidAmount( Integer userId){
        Integer totalPaid = 0;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        timestamp.setDate(1);
        List<PaymentEntity> entities = paymentRepository.paymentsOfMonthByUser(userId,timestamp);
        for(PaymentEntity entity : entities){
            totalPaid = totalPaid + entity.getPaid_amount();
        }
        return totalPaid;
    }
}
