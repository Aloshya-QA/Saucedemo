package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CheckoutInfo {

    private final String
            firstName,
            lastName,
            postalCode;

}
