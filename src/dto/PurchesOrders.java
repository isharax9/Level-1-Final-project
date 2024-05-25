/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author vidur
 */
@AllArgsConstructor
@NoArgsConstructor //generating a No args constructure , LOMBOC ANNOTATION LIB
@Data // setters and getters
public class PurchesOrders {
 
    private int id;
    private int supplierID;
    private Supplier supplier;
    private LocalDateTime poDate;
    private PoStatus poStatus;
    private List<PurchesOrderItem> poItems;
    
}
