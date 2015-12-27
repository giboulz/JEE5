package com.gbz.tuto.test;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "userData", eager = true)
@SessionScoped
public class ajaxTest implements Serializable {

	private static final long serialVersionUID = 2L;

	private String name;

	private int taille;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWelcomeMessage() {
		return "Hello " + name;
	}
	
	
	public String getTailleMessage(){
		if(name != null){
		taille = name.length(); 
		return "mon test :" +Integer.toString(taille);
		}
		return "Pas de nom, pas de longeur, pas de longeur, pas de longeur"; 
	}

}