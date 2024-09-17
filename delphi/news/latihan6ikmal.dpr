program latihan6ikmal;

uses
  Vcl.Forms,
  Unit1 in 'Unit1.pas' {latihan6};

{$R *.res}

begin
  Application.Initialize;
  Application.MainFormOnTaskbar := True;
  Application.CreateForm(Tlatihan6, latihan6);
  Application.Run;
end.
