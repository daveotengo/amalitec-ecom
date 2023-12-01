package com.amalitec.amalitececom.request_response.graphql.input;

import com.amalitec.amalitececom.blueprint.BaseInput;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdateOrderInput extends BaseInput {

    private List<ProductLineInput> productLineInputList;
    private UUID userId;

}