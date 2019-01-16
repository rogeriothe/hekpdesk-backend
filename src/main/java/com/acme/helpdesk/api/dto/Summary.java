package com.acme.helpdesk.api.dto;

import java.io.Serializable;

public class Summary implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer ammountNew;
	private Integer ammountResolved;
	private Integer ammountApproved;
	private Integer ammountDisapproved;
	private Integer ammountAssigned;
	private Integer ammountClosed;

	public Integer getAmmountNew() {
		return ammountNew;
	}

	public void setAmmountNew(Integer ammountNew) {
		this.ammountNew = ammountNew;
	}

	public Integer getAmmountResolved() {
		return ammountResolved;
	}

	public void setAmmountResolved(Integer ammountResolved) {
		this.ammountResolved = ammountResolved;
	}

	public Integer getAmmountApproved() {
		return ammountApproved;
	}

	public void setAmmountApproved(Integer ammountApproved) {
		this.ammountApproved = ammountApproved;
	}

	public Integer getAmmountDisapproved() {
		return ammountDisapproved;
	}

	public void setAmmountDisapproved(Integer ammountDisapproved) {
		this.ammountDisapproved = ammountDisapproved;
	}

	public Integer getAmmountAssigned() {
		return ammountAssigned;
	}

	public void setAmmountAssigned(Integer ammountAssigned) {
		this.ammountAssigned = ammountAssigned;
	}

	public Integer getAmmountClosed() {
		return ammountClosed;
	}

	public void setAmmountClosed(Integer ammountClosed) {
		this.ammountClosed = ammountClosed;
	}

}
