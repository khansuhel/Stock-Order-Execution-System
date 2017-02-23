package com.sahaj.soes.poc.service;

import com.sahaj.soes.poc.entities.Order;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sukhan on 1/1/2017.
 */
@Service
public class StockProcessingEngineImpl {

    private static final Logger LOG = Logger.getLogger(StockProcessingEngineImpl.class);

    public List<Order> execute(List<Order> inputList) {
        for (int i = 0; i < inputList.size(); i++) {
            for (int j = 0; j < inputList.size(); j++) {
                if (i != j) {
                    filterOpenOrders(inputList.get(i), inputList.get(j));
                }
            }
        }
        for (Order o : inputList)
            System.out.println(o.toString());
        return inputList;
    }


    public void filterOpenOrders(Order order1, Order order2){
        if(order1.getCompany().equalsIgnoreCase(order2.getCompany())){
            if(!"CLOSED".equalsIgnoreCase(order1.getStatus())
                    && !"CLOSED".equalsIgnoreCase(order2.getStatus())){
                if(!order1.getSide().equalsIgnoreCase(order2.getSide())){
                    processOpenOrders(order1,order2);
                }
            }
        }
    }



    public void processOpenOrders(Order order1, Order order2){
            int temp1,temp2;
            if(null!=order1.getBalance() && Integer.valueOf(order1.getBalance())>0){
                temp1 = Integer.valueOf(order1.getBalance());
            }else{
                temp1 = Integer.valueOf(order1.getQuantity());
            }
            if(null!=order2.getBalance() && Integer.valueOf(order2.getBalance())>0){
                temp2 = Integer.valueOf(order2.getBalance());
            }else{
                temp2 = Integer.valueOf(order2.getQuantity());
            }

            if( temp1 <= temp2){
                int balance = temp2 - temp1;
                order1.setBalance("0");
                order1.setStatus("CLOSED");
                order2.setBalance(String.valueOf(balance));
                order2.setStatus("OPEN");
            }else{
                int balance = temp1 - temp2;
                order2.setBalance("0");
                order2.setStatus("CLOSED");
                order1.setBalance(String.valueOf(balance));
                order1.setStatus("OPEN");
            }
    }


}
