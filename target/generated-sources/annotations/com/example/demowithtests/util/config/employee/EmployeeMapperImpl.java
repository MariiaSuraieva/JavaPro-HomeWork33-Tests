package com.example.demowithtests.util.config.employee;

import com.example.demowithtests.domain.Address;
import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.domain.Employee.EmployeeBuilder;
import com.example.demowithtests.domain.Photo;
import com.example.demowithtests.domain.Photo.PhotoBuilder;
import com.example.demowithtests.dto.employee.AddressDto;
import com.example.demowithtests.dto.employee.EmployeeDto;
import com.example.demowithtests.dto.employee.EmployeeReadDto;
import com.example.demowithtests.dto.employee.PhotoDto;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-24T13:55:19+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        EmployeeBuilder employee = Employee.builder();

        employee.name( employeeDto.name );
        employee.country( employeeDto.country );
        employee.email( employeeDto.email );
        employee.addresses( addressDtoSetToAddressSet( employeeDto.addresses ) );
        employee.photos( photoDtoSetToPhotoSet( employeeDto.photos ) );

        return employee.build();
    }

    @Override
    public Address map(AddressDto addressDto) {
        if ( addressDto == null ) {
            return null;
        }

        Address address = new Address();

        address.setAddressHasActive( addressDto.addressHasActive );
        address.setCountry( addressDto.country );
        address.setCity( addressDto.city );
        address.setStreet( addressDto.street );

        return address;
    }

    @Override
    public Photo map(PhotoDto value) {
        if ( value == null ) {
            return null;
        }

        PhotoBuilder photo = Photo.builder();

        photo.linkToPhoto( value.linkToPhoto );
        photo.x( value.x );
        photo.y( value.y );
        photo.createdDate( value.createdDate );

        return photo.build();
    }

    @Override
    public EmployeeDto employeeToEmployeeDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        String name = null;
        String country = null;
        String email = null;
        Set<AddressDto> addresses = null;
        Set<PhotoDto> photos = null;

        name = employee.getName();
        country = employee.getCountry();
        email = employee.getEmail();
        addresses = addressSetToAddressDtoSet( employee.getAddresses() );
        photos = photoSetToPhotoDtoSet( employee.getPhotos() );

        EmployeeDto employeeDto = new EmployeeDto( name, country, email, addresses, photos );

        return employeeDto;
    }

    @Override
    public EmployeeReadDto employeeToEmployeeReadDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeReadDto employeeReadDto = new EmployeeReadDto();

        employeeReadDto.name = employee.getName();
        employeeReadDto.country = employee.getCountry();
        employeeReadDto.email = employee.getEmail();

        return employeeReadDto;
    }

    @Override
    public Employee employeeReadDtoToEmployee(EmployeeReadDto employeeReadDto) {
        if ( employeeReadDto == null ) {
            return null;
        }

        EmployeeBuilder employee = Employee.builder();

        employee.name( employeeReadDto.name );
        employee.country( employeeReadDto.country );
        employee.email( employeeReadDto.email );

        return employee.build();
    }

    protected Set<Address> addressDtoSetToAddressSet(Set<AddressDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Address> set1 = new HashSet<Address>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( AddressDto addressDto : set ) {
            set1.add( map( addressDto ) );
        }

        return set1;
    }

    protected Set<Photo> photoDtoSetToPhotoSet(Set<PhotoDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Photo> set1 = new HashSet<Photo>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PhotoDto photoDto : set ) {
            set1.add( map( photoDto ) );
        }

        return set1;
    }

    protected AddressDto addressToAddressDto(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDto addressDto = new AddressDto();

        addressDto.addressHasActive = address.getAddressHasActive();
        addressDto.country = address.getCountry();
        addressDto.city = address.getCity();
        addressDto.street = address.getStreet();

        return addressDto;
    }

    protected Set<AddressDto> addressSetToAddressDtoSet(Set<Address> set) {
        if ( set == null ) {
            return null;
        }

        Set<AddressDto> set1 = new HashSet<AddressDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Address address : set ) {
            set1.add( addressToAddressDto( address ) );
        }

        return set1;
    }

    protected PhotoDto photoToPhotoDto(Photo photo) {
        if ( photo == null ) {
            return null;
        }

        PhotoDto photoDto = new PhotoDto();

        photoDto.linkToPhoto = photo.getLinkToPhoto();
        photoDto.x = photo.getX();
        photoDto.y = photo.getY();
        photoDto.createdDate = photo.getCreatedDate();

        return photoDto;
    }

    protected Set<PhotoDto> photoSetToPhotoDtoSet(Set<Photo> set) {
        if ( set == null ) {
            return null;
        }

        Set<PhotoDto> set1 = new HashSet<PhotoDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Photo photo : set ) {
            set1.add( photoToPhotoDto( photo ) );
        }

        return set1;
    }
}
