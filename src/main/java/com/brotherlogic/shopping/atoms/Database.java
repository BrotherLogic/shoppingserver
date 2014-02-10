package com.brotherlogic.shopping.atoms;

public interface Database<X> {

	public void save(X o);

	public X load(Object index);

}
