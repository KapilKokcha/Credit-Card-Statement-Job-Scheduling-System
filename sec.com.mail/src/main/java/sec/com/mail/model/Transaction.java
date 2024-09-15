package sec.com.mail.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Transaction {
    private long id;
    private String transactionFor;
    private Double transactionAmount;
    private LocalDateTime transactionDateTime;
    private User user;
}
