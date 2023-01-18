import { getExcel } from "../../api/excel.api";
function ExportExcel() {
  const download = async () => {
    try {
      await getExcel();
    } catch (error) {
      console.log("get excel error", error);
    }
  };
  return (
    <>
      Export Excel file
      <button onClick={download}> download </button>
    </>
  );
}
export default ExportExcel;
