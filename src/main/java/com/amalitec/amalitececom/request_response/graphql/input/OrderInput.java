package com.amalitec.amalitececom.request_response.graphql.input;

import com.amalitec.amalitececom.blueprint.BaseInput;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderInput extends BaseInput {
    private List<ProductLineInput> productLineInputList;
    private UUID userId;
}
