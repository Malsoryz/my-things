program Project1;

uses
  Vcl.Forms,
  Unit1 in 'Unit1.pas' {FLatihan3};

{$R *.res}

begin
  Application.Initialize;
  Application.MainFormOnTaskbar := True;
  Application.CreateForm(TFLatihan3, FLatihan3);
  Application.Run;
end.
