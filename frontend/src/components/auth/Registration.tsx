import { useState } from "react"
import { registerUser } from "../utils/WebService"
import { Alert } from "react-bootstrap"

const Registration = () => {
    const [registration, setRegistration] = useState({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        address: "",
        phoneNumber: "",
        image: {},
    })

    const [errorMessage, setErrorMessage] = useState("")
    const [successMessage, setSuccessMessage] = useState("")

    const handleInputChange = (e) => {
        setRegistration({ ...registration, [e.target.name]: e.target.value })
    }

    const handleRegistration = async (e) => {
        e.preventDefault();
        try {
            const result = await registerUser(
                registration.firstName,
                registration.lastName,
                registration.email,
                registration.password,
                registration.address,
                registration.phoneNumber,
                registration.image,
            );
            setSuccessMessage(result + "");
            setErrorMessage("");
            setRegistration({
                firstName: "",
                lastName: "",
                email: "",
                password: "",
                address: "",
                phoneNumber: "",
                image: {},
            })
        } catch (error) {
            setSuccessMessage("");
            setErrorMessage(`Registration error: ${error.message}`)
        }

        setTimeout(() => {
            setErrorMessage("");
            setSuccessMessage("");
        }, 3000)
    }

    return (
        <section className="container col-6 my-5">
            {errorMessage && <Alert key={'danger'} variant={'danger'}></Alert>}
            {successMessage && <Alert key={'success'} variant={'success'}></Alert>}

            <h2>Register</h2>
            <form onSubmit={handleRegistration}>
                <div className="mb-3 row">
                    <label htmlFor="firstName" className="col-sm-2 col-form-label">First Name</label>
                </div>
            </form>
        </section>
    )
}