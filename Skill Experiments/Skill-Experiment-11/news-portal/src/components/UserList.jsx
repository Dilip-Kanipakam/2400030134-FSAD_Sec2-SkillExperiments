import React, { useEffect, useState } from "react";

function UserList() {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    fetch("https://jsonplaceholder.typicode.com/users")
      .then(res => res.json())
      .then(data => setUsers(data));
  }, []);

  return (
    <div>
      <h2>API Users</h2>
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

export default UserList;