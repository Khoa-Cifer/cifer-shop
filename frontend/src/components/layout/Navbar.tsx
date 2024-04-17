import { Link, NavLink } from "react-router-dom"

const Navbar = () => {
    return (
        <nav className="navbar px-5 shadow mt-5">
            <div className="container-fluid">
                <Link to={"/"} className="navbar-brand">
                    <span className="hotel-color">Cifer Shop</span>
                </Link>

                <button
                    className="navbar-toggler"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarScroll"
                    aria-controls="navbarScroll"
                    aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>

                <div>
                    <ul className="flex px-5">
                        <li className="nav-item px-5">
                            <NavLink aria-current="page" to={"/product-list"}>
                                Browse all products
                            </NavLink>
                        </li>

                        <li className="nav-item px-5 padd">
                            <NavLink aria-current="page" to={"/category-list"}>
                                Browse all categories
                            </NavLink>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    )
}

export default Navbar
