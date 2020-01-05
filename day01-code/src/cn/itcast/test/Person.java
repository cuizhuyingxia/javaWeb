package cn.itcast.test;

import java.util.Objects;

/**
 * @Author Lian Flower
 * @Date 2019/12/14 13:19
 * @Version 1.0
 */
public class Person {
	private String s1;

	public String getS1() {
		return s1;
	}

	public void setS1(String s1) {
		this.s1 = s1;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Person)) return false;
		Person person = (Person) o;
		return Objects.equals(s1, person.s1);
	}

	@Override
	public int hashCode() {

		return Objects.hash(s1);
	}
}
