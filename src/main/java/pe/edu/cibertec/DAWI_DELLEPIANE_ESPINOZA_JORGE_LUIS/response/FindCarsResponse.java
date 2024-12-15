package pe.edu.cibertec.DAWI_DELLEPIANE_ESPINOZA_JORGE_LUIS.response;

import pe.edu.cibertec.DAWI_DELLEPIANE_ESPINOZA_JORGE_LUIS.dto.CarDto;

public record FindCarsResponse(String code,
                               String error,
                               Iterable<CarDto> cars) {
}
