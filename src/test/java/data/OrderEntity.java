package data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class OrderEntity {
        private String id;
        private String created;
        private String creditId;
        private String paymentId;
    }

