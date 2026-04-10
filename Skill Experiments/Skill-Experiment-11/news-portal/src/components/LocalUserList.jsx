import React, { useEffect, useState } from "react";

function LocalUserList() {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    fetch("/users.json")
      .then(res => res.json())
      .then(data => setUsers(data));
  }, []);

  return (
    <div>
      <h2>Local Users</h2>
      {users.map(u => (
        <div key={u.id}>
          <p>{u.name}</p>
          <p>{u.email}</p>
          <p>{u.phone}</p>
        </div>
      ))}
    </div>
  );
}

export default LocalUserList;