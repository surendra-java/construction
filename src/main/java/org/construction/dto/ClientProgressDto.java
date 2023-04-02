package org.construction.dto;

import lombok.Data;
import org.construction.model.ClientMaster;
import org.construction.model.ClientProgressMaster;
import org.construction.model.ClientProgressStatus;


@Data
public class ClientProgressDto {

    private long clientProgressId;

    private String reason;

    private long clientId;

    private long clientProgressMasterId;

    private long clientProgressStatusId;
}
