import React, { useState } from "react";
import AddStudent from "./components/AddStudent";
import StudentList from "./components/StudentList";

function App() {
  const [refresh, setRefresh] = useState(false);
  const [editStudent, setEditStudent] = useState(null);

  return (
    <div>
      <h1>Student Management System</h1>

      <AddStudent
        refresh={refresh}
        setRefresh={setRefresh}
        editStudent={editStudent}
        setEditStudent={setEditStudent}
      />

      <StudentList
        refresh={refresh}
        setEditStudent={setEditStudent}
      />
    </div>
  );
}

export default App;