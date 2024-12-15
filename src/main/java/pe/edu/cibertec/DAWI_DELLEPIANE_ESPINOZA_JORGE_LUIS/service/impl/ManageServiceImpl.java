package pe.edu.cibertec.DAWI_DELLEPIANE_ESPINOZA_JORGE_LUIS.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DAWI_DELLEPIANE_ESPINOZA_JORGE_LUIS.dto.CarDetailDto;
import pe.edu.cibertec.DAWI_DELLEPIANE_ESPINOZA_JORGE_LUIS.dto.CarDto;
import pe.edu.cibertec.DAWI_DELLEPIANE_ESPINOZA_JORGE_LUIS.entity.Car;
import pe.edu.cibertec.DAWI_DELLEPIANE_ESPINOZA_JORGE_LUIS.repository.CarRepository;
import pe.edu.cibertec.DAWI_DELLEPIANE_ESPINOZA_JORGE_LUIS.service.ManageService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    CarRepository carRepository;

    @Override
    public List<CarDto> getAllCars() throws Exception {

        List<CarDto> cars = new ArrayList<CarDto>();
        Iterable<Car> iterable = carRepository.findAll();
        iterable.forEach(car -> {
            CarDto carDto = new CarDto(car.getCarId(),
                    car.getMake(),
                    car.getModel(),
                    car.getYear(),
                    car.getColor());
            cars.add(carDto);
        });

        return cars;
    }

    public Optional<CarDto> getAllCarsById(int id) throws Exception {

        Optional<Car> optional = carRepository.findById(id);
        return optional.map(car -> new CarDto(car.getCarId(),
                car.getMake(),
                car.getModel(),
                car.getYear(),
                car.getColor()));
    }

    @Override
    public Optional<CarDetailDto> getCarById(int id) throws Exception {

        Optional<Car> optional = carRepository.findById(id);
        return optional.map(car -> new CarDetailDto(car.getCarId(),
                car.getMake(),
                car.getModel(),
                car.getYear(),
                car.getLicensePlate(),
                car.getOwnerName(),
                car.getOwnerContact(),
                car.getPurchaseDate(),
                car.getMileage(),
                car.getEngineType(),
                car.getColor(),
                car.getInsuranceCompany(),
                car.getInsurancePolicyNumber(),
                car.getRegistrationExpirationDate(),
                car.getServiceDueDate()));
    }

    @Override
    public boolean updateCar(CarDto carDto) throws Exception {

        Optional<Car> optional = carRepository.findById(carDto.carId());
        return optional.map(car -> {
            car.setMake(carDto.make());
            car.setModel(carDto.model());
            car.setYear(carDto.year());
            car.setColor(carDto.color());
            carRepository.save(car);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean deleteCarById(int id) throws Exception {

        Optional<Car> optional = carRepository.findById(id);
        return optional.map(car -> {
            carRepository.delete(car);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean addCar(CarDetailDto carDetalilDto, String vin) throws Exception {

        Optional<Car> optional = carRepository.findById(carDetalilDto.carId());
        if(optional.isPresent())
            return false;
        else {
            Car car = new Car();
            car.setMake(carDetalilDto.make());
            car.setModel(carDetalilDto.model());
            car.setYear(carDetalilDto.year());
            car.setVin(vin); //encriptado
            car.setLicensePlate(carDetalilDto.licensePlate());
            car.setOwnerName(carDetalilDto.ownerName());
            car.setOwnerContact(carDetalilDto.ownerContact());
            car.setPurchaseDate(new Date());
            car.setMileage(carDetalilDto.mileage());
            car.setEngineType(carDetalilDto.engineType());
            car.setColor(carDetalilDto.color());
            car.setInsuranceCompany(carDetalilDto.insuranceCompany());
            car.setInsurancePolicyNumber(carDetalilDto.insurancePolicyNumber());
            car.setRegistrationExpirationDate(new Date());
            car.setServiceDueDate(new Date());
            carRepository.save(car);
            return true;
        }
    }
}
