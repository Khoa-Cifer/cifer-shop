import { useLocation } from "react-router-dom";
import Navbar from "../layout/Navbar";

const Home = () => {
    const location = useLocation();
    const message = location.state && location.state.message

    return (
        <section>
            <Navbar />
            This is home
        </section>
    )
}

export default Home