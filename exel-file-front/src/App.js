import "./App.css";
import EmployeeList from "./components/employee/EmployeeList";
import ExportExcel from "./components/excelFile/ExportExcel";

function App() {
  return (
    <>
      <h1>excel file</h1>
      <div>
        <EmployeeList />
      </div>
      <ExportExcel />
    </>
  );
}

export default App;
