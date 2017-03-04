package ec.com.orion.ticket.agency.jpa.control;

import java.io.Serializable;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;

@TransactionAttribute(TransactionAttributeType.REQUIRED)
public abstract class AbstractDao <T extends Serializable> implements Serializable{

	private final Class<T> clase;
	
	@Inject
	private EntityManager em;

	public AbstractDao(Class<T> clase) {
		super();
		this.clase = clase;
	}

	
	public T buscar(Object id){
		return em.find(clase,id);		
	}
	
	public void remove(T entidad){
		em.remove(em.merge(entidad));
		em.flush();
	}
	
	public T actualizar(T entidad){
		T objetoActualizado = em.merge(entidad);
		em.flush();
		return objetoActualizado;
	}
	
	public void persistir(final T entidad){
		em.persist(entidad);
	}
	
	public List<T> buscarTodos(){
		final CriteriaQuery<T> criteriaQuery = em.getCriteriaBuilder().createQuery(clase);
		criteriaQuery.select(criteriaQuery.from(clase));
		return em.createQuery(criteriaQuery).getResultList();
	}
	
	public void borrarTodos(){
		final CriteriaDelete<T> criteriaDelete =  em.getCriteriaBuilder().createCriteriaDelete(clase);
		criteriaDelete.from(clase);
		em.createQuery(criteriaDelete).executeUpdate();
	}
	
	protected Class<T> getClase() {
		return clase;
	}

	protected EntityManager getEm() {
		return em;
	}
	
	
	
}
