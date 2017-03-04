package ec.com.orion.ticket.agency.jpa.entity;

public enum SeatPosition {
	PREFERENCIA("Preferencia", "preferencia"), BUTACA("Butaca", "butaca"), GENERAL("General", "general");
	
	private final String etiqueta;
	private final String representacionBDD;

	public String getEtiqueta() {
		return etiqueta;
	}

	public String getRepresentacionBDD() {
		return representacionBDD;
	}

	private SeatPosition(String etiqueta, String representacionBDD) {
		this.etiqueta = etiqueta;
		this.representacionBDD = representacionBDD;
	}

}
