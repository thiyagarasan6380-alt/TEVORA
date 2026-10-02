import { Link } from "react-router-dom";

function Sidebar() {

    return (
        <div className="w-64 h-screen flex-shrink-0 bg-gradient-to-b from-orange-500 to-orange-700 text-white p-5">

            <div className="mb-10 text-center">

                <div className="text-5xl">
                </div>

                <h1 className="text-2xl font-bold">
                    TEVORA
                </h1>

                <p className="text-sm">
                    Find. Collaborate. Build.
                </p>

            </div>

            <div className="flex flex-col gap-4">

                <Link
                    className="rounded-lg p-3 hover:bg-orange-500"
                    to="/"
                >
                    Home
                </Link>

                <Link
                    className="rounded-lg p-3 hover:bg-orange-500"
                    to="/projects"
                >
                    Projects
                </Link>

                <Link
                    className="rounded-lg p-3 hover:bg-orange-500"
                    to="/MyApplication"
                >
                    MyApplication
                </Link>

                <Link
                    className="rounded-lg p-3 hover:bg-orange-500"
                    to="/createproject"
                >
                    Create Project
                </Link>

                <Link
                    className="rounded-lg p-3 hover:bg-orange-500"
                    to="/login"
                >
                    Login
                </Link>

            </div>

        </div>
    );
}

export default Sidebar;
