const path = require("path");

// stylesheet import할 때 절대경로로 부르기 : ~@/파일명
module.exports = {
  configureWebpack: {
    resolve: {
      alias: {
        "@": path.join(__dirname, "./src"),
      },
    },
  },
};
