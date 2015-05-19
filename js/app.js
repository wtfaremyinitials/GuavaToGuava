var React = require('react');

var keys = obj => Object.keys(obj);

var BASE_API_URL = '/api';
var get = function(endpoint, params, cb) {
    params = keys(params).reduce((o, n) => { o + n + '=' + params[n] + '&' }, '?');
    params = params.substr(0, params.length - 1);
    require('got')(BASE_API_URL + endpoint + params, res => JSON.parse(res));
};

var repeat = function(cb, interval) {
    cb();
    setInterval(cb, interval);
};

class GuavasToGuavas extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            players: [],
            hand: [],
            pid: null,
            gid: null
        };
    }

    componentDidMount() {
        repeat(() => {
            get('/games/', status => this.setState(status));
        }, 1000);
    }

    render() {
        this.state.gid ? this.renderGame() : this.renderMenu();
    }

    renderGame() {
        return (
            <div>
                <Scoreboard />
                <Table />
                <Hand />
            </div>
        );
    }

    renderMenu() {
        return (
            <div>
                <h1>Join Game</h1>
                <h1>Create Game</h1>
            </div>
        );
    }

}

React.render(<GuavasToGuavas/>, document.body);
