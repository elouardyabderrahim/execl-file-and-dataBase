import { useEffect, useState } from "react";
import { getAll } from "../../api/excel.api";
import "./EmployeeList.css";
function EmployeeList() {
  const [employeeList, setEmployeeList] = useState([]);
  useEffect(() => {
    getAll().then((response) => {
      setEmployeeList(response);
      console.log("employees List ", response);
    });
  }, []);

  return (
    <div>
      <h1>List of Employees</h1>
      <table>
        <thead>
          <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Start at</th>
            <th>Salary</th>
            <th>Birthday</th>
          </tr>
        </thead>
        <tbody>
          {employeeList.map((employee) => (
            <tr key={employee.id}>
              <td>{employee.firstName}</td>
              <td>{employee.lastName}</td>
              <td>{employee.startsAt}</td>
              <td>{employee.salary}</td>
              <td>{employee.birthDay}</td>
              <td></td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
    // <>
    //   <p>EmployeeList</p>
    //   {employeeList.map((emp) => (
    //     <p>{emp.firstName}</p>
    //   ))}
    //   {/* <div>{JSON.stringify(employeeList)}</div> */}
    // </>
  );
}
export default EmployeeList;
