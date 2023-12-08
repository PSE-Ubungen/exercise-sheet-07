package de.unistuttgart.iste.sqa.pse.sheet07.homework.immutable;

import java.util.Date;

/**
 * Represents a person with a name and birthdate.
 *
 * @author Levin Kohler
 */
public final class Person {
	// @ private instance invariant name != null && name.length() > 0;
	// @ private instance invariant birthDate != null;

	private final String name;
	private final Date birthDate;

	/*@
	@ requires name != null && name.length() > 0;
	@ requires birthDate != null;
	@ ensures this.name == name;
	@ ensures this.birthDate == birthDate;
	@*/
	/**
	 * Creates a new person with the given name and birthdate.
	 *
	 * @param name Name of the person.
	 * @param birthDate Birth date of the person.
	 */
	public Person(final String name, final Date birthDate) {
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException("A person may not have a null or empty name");
		}
		if (birthDate == null) {
			throw new IllegalArgumentException("A person's birth date must not be null.");
		}
		this.name = name;
		this.birthDate = birthDate;
	}

	/**
	 * @return This person's name.
	 */
	public /*@ pure @*/ String getName() {
		return name;
	}

	/**
	 * @return This person's birth date.
	 */
	public /*@ pure @*/ Date getBirthDate() {
		return birthDate;
	}
}
