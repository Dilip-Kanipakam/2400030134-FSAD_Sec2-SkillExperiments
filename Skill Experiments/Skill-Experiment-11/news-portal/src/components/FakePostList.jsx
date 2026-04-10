import React, { useEffect, useState } from "react";
import axios from "axios";

function FakePostList() {
  const [posts, setPosts] = useState([]);

  const getData = () => {
    axios.get("https://dummyjson.com/posts")
      .then(res => setPosts(res.data.posts));
  };

  useEffect(() => {
    getData();
  }, []);

  return (
    <div>
      <h2>Posts</h2>
      <button onClick={getData}>Refresh</button>

      {posts.map(p => (
        <div key={p.id}>
          <h4>{p.title}</h4>
          <p>{p.body}</p>
        </div>
      ))}
    </div>
  );
}

export default FakePostList;