package senai.systock.model;

import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;

@MappedSuperclass
public class EntidadeBase {
	
	public EntidadeBase() {
	}
	
	public EntidadeBase(Long id) {
		super();
		this.id = id;
	}
	
	@Id
	@GeneratedValue(generator="sequence_gen")
	private Long id;
	
	private boolean ativo;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntidadeBase other = (EntidadeBase) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public Set<ConstraintViolation<Object>> validar() {
		return Validation.buildDefaultValidatorFactory().getValidator().validate(this);
	}
	
	@PrePersist
	private void basePrePersist() {
		ativo = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
}
