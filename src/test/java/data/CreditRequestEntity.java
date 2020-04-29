package data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreditRequestEntity {
    private String id;
    private String bankId;
    private String created;
    private String status;
}
