package pro.jpa2.complexEmbeddable;

import javax.persistence.Embeddable;

@Embeddable
public class CeAddress {
	String street;
	String state;
	String country;
}
