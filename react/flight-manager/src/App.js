import React from 'react';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import Airlines from './Airlines';
import MainPage from './MainPage';
import './App.css';

function App() {
  const title = "QJ MI HUQ FRONTEND";
  return (
    <div className = "App">
      <div className='Content'>
        <h1> {title} </h1>
      </div>


    </div>
  );
}

export default App;
