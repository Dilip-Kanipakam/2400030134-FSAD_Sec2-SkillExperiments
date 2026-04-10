import React, { useEffect, useState } from "react";

function App() {
  const [message, setMessage] = useState("");

  useEffect(() => {
    fetch("http://localhost:8080/api/hello")
      .then((res) => res.text())
      .then((data) => setMessage(data))
      .catch(() => setMessage("Error connecting to backend"));
  }, []);

  return (
    <div>
      <h1>Full Stack App</h1>
      <h2>{message}</h2>
    </div>
  );
}

export default App;