package com.amalitec.amalitececom.request_response.graphql.input;

import com.amalitec.amalitececom.blueprint.BaseInput;
import com.amalitec.amalitececom.model.ProductLine;
import com.amalitec.amalitececom.model.User;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateOrderInput extends BaseInput {
    private List<ProductLine> products;
    private User user;
}
