import { instance } from "../config/axios.config";
import { saveAs } from "file-saver";
///////////////////////////////////////////////// GET /////////////////////////////////////////////
// all
export const getExcel = async () => {
  const response = await instance.get("/download", {
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

/////////////////////////////upload ///////////////////
export const uploadFile = async (file) => {
  const formData = new FormData();

  formData.append("file", file);
  formData.append("fileName", file.name);
  const config = {
    headers: {
      "content-type": "multipart/form-data",
    },
  };
  const response = await instance.post("/upload", formData, config);
  console.log("upload api", response);
  return response;
};
