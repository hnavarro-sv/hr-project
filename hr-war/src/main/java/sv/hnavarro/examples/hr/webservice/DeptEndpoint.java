/**
 * HR
 * 
 * @author 	Hugo Navarro, http://hnavarro-sv.blogspot.com
 * @version 1.0
 * @date	30/11/2011	
 * 
 */
package sv.hnavarro.examples.hr.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import sv.hnavarro.examples.hr.main.DeptMainImpl;
import sv.hnavarro.examples.hr.schema.Dept;
import sv.hnavarro.examples.hr.schema.ObjectFactory;

@WebService(name = "Dept", serviceName = "DeptService", portName = "DeptPort", targetNamespace = "http://hnavarro-sv.blogspot.com")
public class DeptEndpoint {
	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	DeptMainImpl deptWS;

	@WebMethod(operationName = "departmentEmployees")
	public Dept getDepartmentEmployees(
			@WebParam(name = "DepartmentId") Integer departmentId) {

		ObjectFactory of = new ObjectFactory();
		Dept dept = of.createDept();
		dept = deptWS.getDepartmentEmployees(departmentId);

		log.debug("ECHO SERVICE (DEPT): " + dept.getDepartmentId() + " - "
				+ dept.getDepartmentName());

		return dept;
	}

}
