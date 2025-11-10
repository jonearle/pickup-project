/* Clone of the homepage but with a different header */
import './css/Homepage.css'
import { useNavigate } from 'react-router-dom';
import { loginUser } from './services/gameAPI.ts'
import { useState } from 'react';


export default function Homepage() {
  const navigate = useNavigate();

  const [username, setUsername] = useState("")
  const [password, setPassword] = useState("")

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    // stop default page reload
    event.preventDefault();

    try {
      const userLogin = await loginUser(username, password)
      console.log('Logging in', userLogin)
      navigate('/games')
    } catch(error) {
      console.error('Error logging in: ', error)
    }
  }

  function handleUsernameChange(e: React.ChangeEvent<HTMLInputElement>) {
    setUsername(e.target.value);
  }

  function handlePasswordChange(e: React.ChangeEvent<HTMLInputElement>) {
    setPassword(e.target.value);
  }

  // Login button redirect is temporary
  return (
    <div className="main-wrapper">
      <div className='content'>
        <h1 className="headermain">Welcome back.</h1>
        <div className="form-container">
          <form className="hpgauth" onSubmit={handleSubmit}>
            <label htmlFor="username">Username</label> 
            <input type="text" className="username" onChange={handleUsernameChange}/> 
            <label htmlFor="username">Password</label>
            <input type="text" className="password" onChange={handlePasswordChange}/>
            <button className="login-button" type="submit">Log In!</button>
            <span className="or-button">or</span>
            <button className="make-account" type="button">Sign Up!</button>
          </form>
        </div>
      </div>
    </div>
  );
}