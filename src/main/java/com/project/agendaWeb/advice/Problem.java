package com.project.agendaWeb.advice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.agendaWeb.dto.ErrorMessageDto;

import java.util.List;

@JsonInclude
public record Problem(
        int status,
        String message,
        String detail,
        List<ErrorMessageDto> errors
) {
}
