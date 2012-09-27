
#TODO: try tornado
import sys
sys.path.append("/usr/local/lib/python2.7/dist-packages/")
import tornado.ioloop
import tornado.web

class MainHandler(tornado.web.RedirectHandler):
    def get(self):
        self.write("Hello World")


application = tornado.web.Application([
        (r"./", MainHandler),
])

if __name__ == "__main__":
    application.listen(8888)
    tornado.ioloop.IOLoop.instance().start()



