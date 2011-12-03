/**
 * HR
 * 
 * @author 	Hugo Navarro, http://hnavarro-sv.blogspot.com
 * @version 1.0
 * @date	30/11/2011	
 * 
 */
package sv.hnavarro.examples.hr.main;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import oracle.sql.ARRAY;
import oracle.sql.STRUCT;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.jdbc.support.oracle.SqlReturnSqlData;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import sv.hnavarro.examples.hr.adt.SqlEmp;
import sv.hnavarro.examples.hr.schema.Dept;
import sv.hnavarro.examples.hr.schema.Emp;
import sv.hnavarro.examples.hr.schema.Emps;
import sv.hnavarro.examples.hr.schema.ObjectFactory;

@Component
public class DeptMainImpl {
	Logger log = Logger.getLogger(this.getClass());

	private SimpleJdbcCall call;

	@Autowired
	@Qualifier("dataSource")
	public void init(DataSource dataSource) {
		log.debug("Initial!");

		this.call = new SimpleJdbcCall(dataSource)
				.withSchemaName("HR")
				.withCatalogName("PKG_HR")
				.withProcedureName("PROC_DEPT_EMPS")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(
						new SqlParameter("IN_DEPT_ID", Types.NUMERIC),
						new SqlOutParameter("OUT_DEPT_NAME", Types.VARCHAR),
						new SqlOutParameter("OUT_DEPT_EMPS", Types.ARRAY,
								"TYPE_EMPS", new SqlReturnSqlData(SqlEmp.class)

						)

				);
	}

	public Dept getDepartmentEmployees(final Integer departmentId) {
		log.debug("Get!");

		Map<String, Object> in = new LinkedHashMap<String, Object>();
		in.put("IN_DEPT_ID", departmentId);

		@SuppressWarnings("rawtypes")
		Map out = call.execute(in);

		String name = (String) out.get("OUT_DEPT_NAME");
		ARRAY array = (ARRAY) out.get("OUT_DEPT_EMPS");

		ObjectFactory of = new ObjectFactory();
		Dept dept = of.createDept();
		dept.setDepartmentId(departmentId);
		dept.setDepartmentName(name);
		dept.setEmps(transformer(array));

		return dept;
	}

	private Emps transformer(ARRAY array) {
		log.debug("Transformer!");

		ObjectFactory of = new ObjectFactory();
		Emps emps = of.createEmps();

		try {
			ResultSet rs = array.getResultSet();

			while (rs != null && rs.next()) {
				STRUCT struct = (STRUCT) rs.getObject(2);
				Object[] attrs = struct.getAttributes();
				Emp emp = new Emp();

				emp.setFirstName((String) attrs[0]);
				emp.setLastName((String) attrs[1]);
				emp.setEmail((String) attrs[2]);
				emp.setHireDate((Date) attrs[3]);
				emp.setSalary((BigDecimal) attrs[4]);

				emps.getItems().add(emp);
			}

			return emps;
		} catch (SQLException e) {
			throw new DataRetrievalFailureException("Oups!", e);
		}
	}

}
