import { useEffect } from "react";
import { getAll } from "./api/excel.api";
import "./App.css";
import EmployeeList from "./components/employee/EmployeeList";

function App() {

  return (
    <>
      <h1>excel file</h1>
      <div>
        <EmployeeList />
      </div>
    </>
  );
}

export default App;
