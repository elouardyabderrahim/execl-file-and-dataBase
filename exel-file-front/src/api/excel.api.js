import { instance } from "../config/axios.config";
import { saveAs } from "file-saver";
///////////////////////////////////////////////// GET /////////////////////////////////////////////
// all

export const getExcel = async () => {
  const response = await instance.get("/excel", {
    responseType: "arraybuffer",
  });
  const fileName = response.headers["x-suggested-filename"];
  console.log("File name", response.headers);
  const file = new File([response.data], fileName, {
    type: "application/xlsx",
  });
  saveAs(file);
  console.log("inside get excel", response);
  return response;
};
