package pe.edu.cibertec.DAWI_DELLEPIANE_ESPINOZA_JORGE_LUIS.response;

import pe.edu.cibertec.DAWI_DELLEPIANE_ESPINOZA_JORGE_LUIS.dto.CarDetailDto;

public record FindCarByIdResponse(String code,
                                  String error,
                                  CarDetailDto cars) {
}
