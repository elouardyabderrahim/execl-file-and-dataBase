import { instance } from "../config/axios.config";

///////////////////////////////////////////////// GET /////////////////////////////////////////////
// all

export const getAll = async () => {
  const response = await instance.get("/employees");
  console.log("inside get all api", response.data);
  return response.data;
};
