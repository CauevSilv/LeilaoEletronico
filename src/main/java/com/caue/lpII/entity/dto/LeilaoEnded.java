package com.caue.lpII.entity.dto;

import com.caue.lpII.entity.enums.LeilaoStatusTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeilaoEnded {
    private Long leilaoId;
    private LeilaoStatusTypes status;
    private List<WinnersLance> lotesVencedores;
}
