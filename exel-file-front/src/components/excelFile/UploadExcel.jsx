import { useState } from "react";
import { uploadFile } from "../../api/excel.api";

const UploadExcel = ({ onUploadSuccess }) => {
  const [file, setSelectedFile] = useState(null);
  // const [fileChange, setFileChange] = useState(Event.target.files[0]);
  const onChange = (event) => {
    setSelectedFile(event.target.files[0]);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    const data = await uploadFile(file);
    onUploadSuccess((prevState) => !prevState);

    console.log("Data uploaded", data);
  };

  return (
    <div className="App">
      <form onSubmit={handleSubmit}>
        <h1>Upload excel</h1>
        <input type="file" onChange={onChange} />
        <button type="submit">Upload</button>
      </form>
    </div>
  );
};

export default UploadExcel;
