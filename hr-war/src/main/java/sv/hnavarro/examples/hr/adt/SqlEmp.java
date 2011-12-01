/**
 * HR
 * 
 * @author 	Hugo Navarro, http://hnavarro-sv.blogspot.com
 * @version 1.0
 * @date	30/11/2011	
 * 
 */
package sv.hnavarro.examples.hr.adt;

import java.math.BigDecimal;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.Date;

import sv.hnavarro.examples.hr.schema.Emp;

@SuppressWarnings("serial")
public class SqlEmp extends Emp implements SQLData {

	public SqlEmp(String firstName, String lastName, String email,
			Date hireDate, BigDecimal salary) {
		super.firstName = firstName;
		super.lastName = lastName;
		super.email = email;
		super.hireDate = hireDate;
		super.salary = salary;
	}

	@Override
	public String getSQLTypeName() throws SQLException {
		return "TYPE_EMP";
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		super.setFirstName(stream.readString());
		super.setLastName(stream.readString());
		super.setEmail(stream.readString());
		super.setHireDate(new java.util.Date(stream.readDate().getTime()));
		super.setSalary(stream.readBigDecimal());
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(super.getFirstName());
		stream.writeString(super.getLastName());
		stream.writeString(super.getEmail());
		stream.writeDate(new java.sql.Date(super.getHireDate().getTime()));
		stream.writeBigDecimal(super.getSalary());
	}

}