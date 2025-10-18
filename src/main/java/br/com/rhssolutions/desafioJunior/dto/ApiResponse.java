package br.com.rhssolutions.desafioJunior.dto;

import java.time.LocalDateTime;

public record ApiResponse<T>( //Respostas padronizadas para os controllers
                              int status,
                              String message,
                              T data,
                              LocalDateTime timestamp) {

}
