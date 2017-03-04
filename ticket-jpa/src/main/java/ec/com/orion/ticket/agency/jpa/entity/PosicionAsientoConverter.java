package ec.com.orion.ticket.agency.jpa.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PosicionAsientoConverter implements AttributeConverter<SeatPosition,String>{

	@Override
	public String convertToDatabaseColumn(SeatPosition attribute) {
		
		return attribute.getRepresentacionBDD();
	}

	@Override
	public SeatPosition convertToEntityAttribute(String dbData) {
		for(SeatPosition posicion:SeatPosition.values()){
			if(dbData.equals(posicion.getRepresentacionBDD())){
				return posicion;
			}
		}
		throw new IllegalArgumentException("Atributo desconocido" + dbData);
	}

}
