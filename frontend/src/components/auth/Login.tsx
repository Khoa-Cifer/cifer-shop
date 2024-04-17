import { useState } from "react"
import { Link, useLocation, useNavigate } from "react-router-dom"
import { loginUser } from "../utils/WebService"
import { useAuth } from "./AuthProvider"

const Login = () => {
    const [errorMessage, setErrorMessage] = useState("")
    const [login, setLogin] = useState({
        email: "",
        password: ""
    })

    const navigate = useNavigate();
    const auth = useAuth();
    const location = useLocation();
    const redirectUrl = location.state?.path || "/"

    const handleInputChange = (e) => {
        setLogin({
            ...login,
            [e.target.name]: e.target.value
        });
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        const success = await loginUser(login)
        if (success) {
            const token = success.token;
            auth.handleLogin(token);
            navigate(redirectUrl, { replace: true });
        } else {
            setErrorMessage("Invalid username or password")
        }
        setTimeout(() => {
            setErrorMessage("");
        }, 4000)
    }

    return (
        <section className="container col-6 my-5">
            {errorMessage &&
                <p className="alert alert-danger">{errorMessage}</p>}
            <h2>Login</h2>
            <form onSubmit={handleSubmit}>
                <div className="row mb-3">
                    <label className="col-sm-2 col-form-label" htmlFor="email">Email</label>
                    <input
                        id="email"
                        name="email"
                        type="email"
                        className="form-control"
                        value={login.email}
                        onChange={handleInputChange}
                    />

                    <label className="col-sm-2 col-form-label" htmlFor="password">Password</label>
                    <input
                        id="password"
                        name="password"
                        type="password"
                        className="form-control"
                        value={login.password}
                        onChange={handleInputChange}
                    />
                </div>

                <div className="mb-3">
                    <button className="btn btn-hotel" type="submit" style={{ marginRight: "10px" }}>
                        Login
                    </button>
                    <span className="" style={{ marginLeft: "10px" }}>
                        Don't have a account yet ? || <Link to={"/register"}>Register</Link>
                    </span>
                </div>
            </form>
        </section>
    )
}

export default Login